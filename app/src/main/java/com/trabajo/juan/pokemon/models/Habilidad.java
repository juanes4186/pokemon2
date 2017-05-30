package com.trabajo.juan.pokemon.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Habilidad {
    @SerializedName("is_hidden")
    @Expose
    private Boolean isHidden;
    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("ability")
    @Expose
    private HabilidadRespuesta ability;

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public HabilidadRespuesta getAbility() {
        return ability;
    }

    public void setAbility(HabilidadRespuesta ability) {
        this.ability = ability;
    }


}
