package com.slothbear.springhttp;

import java.util.Objects;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class SpringHttpApplication {

	private static final String URI = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

	@Bean
	CurrencyService currencyService() {
		final var webClient = WebClient.create(URI);

		return HttpServiceProxyFactory.builder()
				.clientAdapter(WebClientAdapter.forClient(webClient))
				.build()
				.createClient(CurrencyService.class);
	}

	@Bean
	ApplicationRunner init(final CurrencyService currencyService) {
		return args -> {
			final var restTemplate = new RestTemplate();
			final var resForRestTemplate = restTemplate.getForObject(URI, Currency.class);
			System.out.println(getUsd(resForRestTemplate));

			final var webClient = WebClient.create(URI);
			final var resForWebClient = getUsd(webClient.get()
					.exchangeToMono(response -> response.bodyToMono(Currency.class))
					.block());
			System.out.println(resForWebClient);

			final var resForHttpInterface = currencyService.get();
			System.out.println(getUsd(resForHttpInterface));
		};
	}

	private String getUsd(final Currency currency) {
		return Objects.requireNonNull(currency).getUsd();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringHttpApplication.class, args);
	}

	static class Currency {
		private String usd;

		public String getUsd() {
			return usd;
		}
	}

	interface CurrencyService {

		@GetExchange(URI)
		Currency get();
	}
}
