package com.river.demo.domain.settlements.batch.config;

import com.river.demo.domain.orders.model.entity.OrderItem;
import com.river.demo.domain.settlements.batch.listener.SettlementBatchJobMetricLogger;
import com.river.demo.domain.settlements.batch.processor.SettlementConvertProcessor;
import com.river.demo.domain.settlements.batch.processor.SettlementProcessor;
import com.river.demo.domain.settlements.batch.reader.EligibleOrderItemReader;
import com.river.demo.domain.settlements.batch.reader.SettlementDataReader;
import com.river.demo.domain.settlements.batch.writer.SettlementDataWriter;
import com.river.demo.domain.settlements.batch.writer.SettlementStatusWriter;
import com.river.demo.domain.settlements.model.entity.Settlement;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class SettlementStepConfiguration {

    @Value("${custom.batch.chunk.size}")
    private Integer batchSize;

    @Bean
    @Qualifier("prepareSettlementDataStep")
    public Step prepareSettlementDataStep(
            JobRepository jobRepository
            , PlatformTransactionManager txManager
            , EligibleOrderItemReader eligibleOrderItemReader
            , SettlementConvertProcessor settlementConvertProcessor
            , SettlementDataWriter settlementDataWriter
    ) {
        return new StepBuilder("prepareSettlementDataStep", jobRepository)
                .<OrderItem, Settlement>chunk(batchSize, txManager)

                .reader(eligibleOrderItemReader)

                .processor(settlementConvertProcessor)

                .writer(settlementDataWriter)

                .listener(stepMetricLogger())

                .build();
    }

    @Bean
    @Qualifier("processSettlementStep")
    public Step processSettlementStep(
            JobRepository jobRepository
            , PlatformTransactionManager txManager
            , SettlementDataReader settlementDataReader
            , SettlementProcessor settlementProcessor
            , SettlementStatusWriter settlementStatusWriter
    ) {
        return new StepBuilder("processSettlementStep", jobRepository)
                .<Settlement, Settlement>chunk(batchSize, txManager)

                .reader(settlementDataReader)

                .processor(settlementProcessor)

                .writer(settlementStatusWriter)

                .listener(stepMetricLogger())

                // 예외 발생시 건너뛰고 계속 진행 (최대 1000개까지 허용)
                .faultTolerant()
                .skipLimit(1000)
                .skip(Exception.class)

                // 재시도 설정 (최대 3번 재시도)
                .retryLimit(3)
                .retry(Exception.class)

                .build();
    }

    @Bean
    public StepExecutionListener stepMetricLogger() {
        return new SettlementBatchJobMetricLogger();
    }
}
