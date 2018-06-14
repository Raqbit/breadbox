package xyz.breadbox;

public class BreadboxApplication {

    private final String token;

    BreadboxApplication(String token) {
        this.token = token;
        System.out.println(String.format("Token: %s", token));
    }

    /**
     * Run the Discord Bot.
     *
     * @param args the command line parameters
     */
    public static void main(String[] args) {
        BreadboxApplication app = new BreadboxApplication(args[0]);
    }

    String getToken() {
        return token;
    }
}
