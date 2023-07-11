package com.eukolos.mail2operator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class InvoiceElements {
    private String invoiceNumber;
    private String invoiceDate;
    private String totalPrice;
    private String currency;
    private String invoiceSender;
}
