package com.guilherme.cursomc.domain.enums;

public enum PaymentState {

    PENDING(1, "Pending"), SETTLED(2, "Settled"), CANCELED(3, "Canceled");

    private int cod;
    private String description;

    PaymentState(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentState toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (PaymentState pay : PaymentState.values()) {
            if (cod.equals(pay.getCod())) {
                return pay;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
