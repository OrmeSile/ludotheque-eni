package dev.orme.ludotheque.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class ImageInfo implements Comparable<ImageInfo>{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String path;
    private String alt;
    private int imageOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @Override
    public int compareTo(ImageInfo o) {
        return this.imageOrder - o.imageOrder;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public int getImageOrder() {
        return imageOrder;
    }

    public void setImageOrder(int imageOrder) {
        this.imageOrder = imageOrder;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
