package models;

public class Photos {

    private Integer albumId;
    private String albumTitle;
    private String albumUrl;
    private String albumThumbnailUrl;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public String getAlbumThumbnailUrl() {
        return albumThumbnailUrl;
    }

    public void setAlbumThumbnailUrl(String albumThumbnailUrl) {
        this.albumThumbnailUrl = albumThumbnailUrl;
    }
}
