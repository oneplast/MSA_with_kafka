package com.river.demo.domain.products.api;

import com.river.core.model.web.BaseResponse;
import com.river.core.model.web.PageResponse;
import com.river.demo.domain.products.mapper.ProductMapper;
import com.river.demo.domain.products.model.dto.ProductDescription;
import com.river.demo.domain.products.model.dto.UpsertProductRequest;
import com.river.demo.domain.products.model.entity.Product;
import com.river.demo.domain.products.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApiController {

    private final ProductService productService;

    @GetMapping
    public PageResponse<List<ProductDescription>> getProducts(
            @PageableDefault Pageable pageable
    ) {
        Page<Product> result = productService.getProducts(pageable);
        return new PageResponse<>(
                pageable.getPageNumber(),
                result.getTotalPages(),
                pageable.getPageSize(),
                result.hasNext(),
                result.map(ProductMapper::toDescription)
                        .toList()
        );
    }

    @GetMapping("/{code}")
    public BaseResponse<ProductDescription> getDescription(
            @PathVariable String code
    ) {
        Product product = productService.getByCode(code);
        return new BaseResponse<>(
                ProductMapper.toDescription(product),
                "상품을 성공적으로 조회하였습니다."
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ProductDescription> create(
            @RequestBody @Valid UpsertProductRequest request,
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode
            @RequestHeader("X-CODE") String accountCode

    ) {
        Product saved = productService.save(accountCode, request);
        return new BaseResponse<>(
                ProductMapper.toDescription(saved),
                "상품을 성공적으로 등록하였습니다."
        );
    }

    @PatchMapping("/{code}")
    public BaseResponse<ProductDescription> update(
            @PathVariable String code,
            @RequestBody @Valid UpsertProductRequest request,
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode
            @RequestHeader("X-CODE") String accountCode
    ) {
        Product updated = productService.update(accountCode, code, request);
        return new BaseResponse<>(
                ProductMapper.toDescription(updated),
                "상품을 성공적으로 수정하였습니다."
        );
    }

    @DeleteMapping("/{code}")
    public BaseResponse<String> delete(
            @PathVariable String code,
//            @AuthenticationPrincipal(expression = "accountCode") String accountCode
            @RequestHeader("X-CODE") String accountCode
    ) {
        String deleted = productService.delete(accountCode, code);
        return new BaseResponse<>(
                deleted,
                "상품을 성공적으로 삭제하였습니다."
        );
    }


}
