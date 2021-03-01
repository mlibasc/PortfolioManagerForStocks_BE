package com.api.stocks;

import com.api.stocks.entity.FXSpot;
import com.api.stocks.entity.Portfolio;
import com.api.stocks.entity.Stock;
import com.api.stocks.repository.PortfolioRepository;
import com.api.stocks.repository.StockRepository;
import com.api.stocks.repository.FXSpotRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StocksApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(StocksApplication.class, args);
		PortfolioRepository portfolioRepo = configurableApplicationContext.getBean(PortfolioRepository.class);
		StockRepository stockRepo = configurableApplicationContext.getBean(StockRepository.class);
		FXSpotRepository fxSpotRepo = configurableApplicationContext.getBean(FXSpotRepository.class);
		
		Portfolio alfredPortfolio = new Portfolio();
		Portfolio manfredPortfolio = new Portfolio();
		Portfolio malfredPortfolio = new Portfolio();
		alfredPortfolio.setClientName("Alfred");
		alfredPortfolio.setPortfolioName("High Risk");
		alfredPortfolio.setPortfolioCurrency("CAD");
		manfredPortfolio.setClientName("Manfred");
		manfredPortfolio.setPortfolioName("Long Term Savings");
		manfredPortfolio.setPortfolioCurrency("USD");
		malfredPortfolio.setClientName("Malfred");
		malfredPortfolio.setPortfolioName("Diverse Mix");
		malfredPortfolio.setPortfolioCurrency("GBP");

		List<Portfolio> portfolios = Arrays.asList(alfredPortfolio, manfredPortfolio, malfredPortfolio);

		Stock stockGME = new Stock();
		Stock stockBB = new Stock();
		Stock stockAAPL = new Stock();
		stockGME.setSymbol("GME");
		stockGME.setCurrency("USD");
		stockGME.setPrice(new BigDecimal(168));
		stockBB.setSymbol("BB");
		stockBB.setCurrency("CAD");
		stockBB.setPrice(new BigDecimal(10.05));
		stockAAPL.setSymbol("AAPL");
		stockAAPL.setCurrency("USD");
		stockAAPL.setPrice(new BigDecimal(121.26));
		List<Stock> stocks = Arrays.asList(stockGME, stockBB, stockAAPL);
		stockRepo.saveAll(stocks);

		FXSpot fxSpot1 = new FXSpot();
		FXSpot fxSpot2 = new FXSpot();
		FXSpot fxSpot3 = new FXSpot();
		FXSpot fxSpot4 = new FXSpot();
		// Set from and to currencies and rates
		fxSpot1.setFromCurrency("CAD");
		fxSpot1.setToCurrency("USD");
		fxSpot1.setRate(new BigDecimal(0.79));
		fxSpot2.setFromCurrency("USD");
		fxSpot2.setToCurrency("CAD");
		fxSpot2.setRate(new BigDecimal(1.27));
		fxSpot3.setFromCurrency("GBP");
		fxSpot3.setToCurrency("CAD");
		fxSpot3.setRate(new BigDecimal(1.78));
		fxSpot4.setFromCurrency("CAD");
		fxSpot4.setToCurrency("GBP");
		fxSpot4.setRate(new BigDecimal(0.56));
		List<FXSpot> fxSpots = Arrays.asList(fxSpot1, fxSpot2, fxSpot3, fxSpot4);

		fxSpotRepo.saveAll(fxSpots);

		alfredPortfolio.addStockToPortfolio(stockGME, new BigDecimal(1));
		alfredPortfolio.addStockToPortfolio(stockBB, new BigDecimal(1));
		manfredPortfolio.addStockToPortfolio(stockAAPL, new BigDecimal(1));
		malfredPortfolio.addStockToPortfolio(stockGME, new BigDecimal(1));

		portfolioRepo.saveAll(portfolios);
	}
}
