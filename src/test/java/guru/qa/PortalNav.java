package guru.qa;

public enum PortalNav {
    PORTAL_NAV_PORTAL("Портал"),
    PORTAL_NAV_EDU  ("Тренинги");

    public final String desc;

    PortalNav(String desc) {
        this.desc = desc;
    }
}
