package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoicePaymentRes {
    private String invoice_no;
    private String payment_link;
}
