package com.stock.service.core;

public abstract class AbstractDTO {
    private Long id;
    private Long creatorId;

    public AbstractDTO() {
    }

    public AbstractDTO(Long id, Long creatorId) {
        this.id = id;
        this.creatorId = creatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
