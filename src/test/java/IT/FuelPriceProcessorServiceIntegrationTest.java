package IT;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.project.fuel_price_notifier.service.FuelPriceProcessorService;
import com.project.fuel_price_notifier.service.scheduler.SchedulerConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest
@ExtendWith(MockitoExtension.class)
public class FuelPriceProcessorServiceIntegrationTest {
    @Mock
    FuelPriceProcessorService processorService;

    @Test
    void testIT() throws IOException {
        SchedulerConfig schedulerConfig = new SchedulerConfig(processorService);

        stubFor(post("").willReturn(aResponse()
                .withStatus(200)));

        schedulerConfig.getFuelPrices();

        verify(postRequestedFor(urlEqualTo("")));
    }
}
