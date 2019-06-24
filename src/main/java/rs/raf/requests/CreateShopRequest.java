package rs.raf.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateShopRequest {

    @NotNull(message = "Shop name must not be blank")
    @NotBlank(message = "Shop name must not be blank")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
