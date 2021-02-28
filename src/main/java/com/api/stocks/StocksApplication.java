package com.api.stocks;

import com.api.stocks.entity.Portfolio;
import com.api.stocks.entity.Stock;
import com.api.stocks.repository.PortfolioRepository;
import com.api.stocks.repository.StockRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StocksApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(StocksApplication.class, args);
		PortfolioRepository portfolioRepo = configurableApplicationContext.getBean(PortfolioRepository.class);
		StockRepository stockRepo = configurableApplicationContext.getBean(StockRepository.class);
		
		Portfolio alfredPortfolio = new Portfolio();
		Portfolio manfredPortfolio = new Portfolio();
		Portfolio meiPortfolio = new Portfolio();
		List<Portfolio> portfolios = Arrays.asList(alfredPortfolio, manfredPortfolio, meiPortfolio);

		Stock stockGME = new Stock();
		Stock stockBB = new Stock();
		Stock stockAAPL = new Stock();
		List<Stock> stocks = Arrays.asList(stockGME, stockBB, stockAAPL);

		stockRepo.saveAll(stocks);

		alfredPortfolio.addStockToPortfolio(stockGME);
		alfredPortfolio.addStockToPortfolio(stockBB);
		manfredPortfolio.addStockToPortfolio(stockAAPL);
		meiPortfolio.addStockToPortfolio(stockGME);

		portfolioRepo.saveAll(portfolios);

	}
}
