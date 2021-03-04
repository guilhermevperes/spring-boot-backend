package com.guilherme.cursomc.domain.enums;

public enum ClientType {

    PESSOAFISICA(1, "PessoaFisica"), PESSOAJURIDICA(2, "PessoaJuridica");

    private int cod;
    private String description;

    ClientType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static ClientType toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (ClientType cli : ClientType.values()) {
            if (cod.equals(cli.getCod())) {
                return cli;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
