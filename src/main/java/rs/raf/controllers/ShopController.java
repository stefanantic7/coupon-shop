package rs.raf.controllers;

import rs.raf.models.Coupon;
import rs.raf.models.Shop;
import rs.raf.repositories.CouponRepository;
import rs.raf.repositories.ShopRepository;
import rs.raf.services.ShopService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/shops")
public class ShopController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> get() {
        return new ArrayList<>();
    }

}
