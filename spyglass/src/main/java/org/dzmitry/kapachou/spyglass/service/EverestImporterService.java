package org.dzmitry.kapachou.spyglass.service;

import io.debezium.data.Envelope;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.spyglass.entity.Portfolio;
import org.dzmitry.kapachou.spyglass.service.repo.PortfolioRepository;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class EverestImporterService {
    private final PortfolioRepository portfolioRepository;

    public void replicateData(Portfolio portfolio, Envelope.Operation operation) {
        if (Envelope.Operation.DELETE == operation) {
            portfolioRepository.deleteById(portfolio.getId());
        } else {
            portfolioRepository.save(portfolio);
        }
        log.info("portfolios #: [{}]", portfolioRepository.count());
    }
}
