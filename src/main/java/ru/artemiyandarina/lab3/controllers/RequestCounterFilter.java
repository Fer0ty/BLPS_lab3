package ru.artemiyandarina.lab3.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class RequestCounterFilter implements Filter {

    private final Counter requestCounter;
    private final AtomicLong requestCount = new AtomicLong(0);

    public RequestCounterFilter(MeterRegistry meterRegistry) {
        requestCounter = meterRegistry.counter("http.requests.total");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        requestCounter.increment();
        requestCount.incrementAndGet();
        chain.doFilter(request, response);
    }

    public long getRequestCount() {
        return requestCount.getAndSet(0); // Обнулить счетчик после чтения
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}


