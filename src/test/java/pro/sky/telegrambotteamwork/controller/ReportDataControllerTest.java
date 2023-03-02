package pro.sky.telegrambotteamwork.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambotteamwork.model.ReportData;
import pro.sky.telegrambotteamwork.serviceImpl.ReportDataService;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportDataController.class)
class ReportDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportDataService reportDataService;

    @Test
    void downloadReport() throws Exception {
        String ration = "good ration";
        String health = "health";
        ReportData reportData = new ReportData();
        reportData.setHealth(health);
        reportData.setRation(ration);
        when(reportDataService.findById(anyLong())).thenReturn(reportData);

        mockMvc.perform(
                        get("/photoReports/{id}/report", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.health").value(health))
                .andExpect(jsonPath("$.ration").value(ration));
        verify(reportDataService).findById(1L);
    }


}