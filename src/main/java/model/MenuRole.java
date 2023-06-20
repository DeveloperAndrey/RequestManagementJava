package model;

public class MenuRole {
    private int id;
    private Menu menu;
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MenuRole{" +
                "id=" + id +
                ", menu=" + menu +
                ", role=" + role +
                '}';
    }
}
