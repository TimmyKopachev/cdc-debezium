package org.dzmitry.kapachou.spyglass.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.data.Envelope;
import lombok.AllArgsConstructor;
import org.dzmitry.kapachou.spyglass.entity.Portfolio;
import org.dzmitry.kapachou.spyglass.service.repo.AssetRepository;
import org.dzmitry.kapachou.spyglass.service.repo.PortfolioRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class EverestImporterService {

    private final PortfolioRepository portfolioRepository;

    private final AssetRepository assetRepository;

    public void replicateData(Map<String, Object> customerData, Envelope.Operation operation) {
        final ObjectMapper mapper = new ObjectMapper();
        final Portfolio portfolio = mapper.convertValue(customerData, Portfolio.class);

        if (Envelope.Operation.DELETE == operation) {
            //customerRepository.deleteById(customer.getId());
        } else {
            //customerRepository.save(customer);
        }
    }
}
