package com.guilherme.cursomc.domain.enums;

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"), CLIENT(2, "ROLE_CLIENT");

    private int cod;
    private String description;

    private Perfil(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Perfil p : Perfil.values()) {
            if (cod.equals(p.getCod())) {
                return p;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
