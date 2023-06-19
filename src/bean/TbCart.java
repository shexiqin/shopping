package bean;

public class TbCart {
   private String price;
   private String goodsName;
   private String cartCount;
   private String cartId;

    public String getPrice() {
        return price;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getCartCount() {
        return cartCount;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setCartCount(String cartCount) {
        this.cartCount = cartCount;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
