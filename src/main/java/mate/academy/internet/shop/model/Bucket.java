package mate.academy.internet.shop.model;

import java.util.List;

public class Bucket {
    private Long id;
    private List<Item> items;
    private User user;

    public Bucket(List<Item> items, User user) {
        this.items = items;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id
                + ", items=" + items
                + ", user=" + user
                + '}';
    }
}
