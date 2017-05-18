package cn.zhuangh7.contact;

/**
 * Using to provid a two kind of layout itemlist
 * <p>
 * Created by Zhuangh7 on 2017/5/18.HUAWEI_SB
 */

public class contacter {
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAddr() {
        return addr;
    }

    public String getTel_m() {
        return tel_m;
    }

    public String getTel_s() {
        return tel_s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setTel_m(String tel_m) {
        this.tel_m = tel_m;
    }

    public void setTel_s(String tel_s) {
        this.tel_s = tel_s;
    }

    private String name;
    private int id;
    private String addr;
    private String tel_m;
    private String tel_s;

    private contacter(){

    }
    public static contacter newContact(String name,String addr,String tel_m,String tel_s){
        contacter c = new contacter();
        c.setAddr(addr);
        c.setName(name);
        c.setTel_m(tel_m);
        c.setTel_s(tel_s);

        return c;
    }

    public static contacter newContact(int id,String name,String addr,String tel_m,String tel_s){
        contacter c = new contacter();
        c.id= id;
        c.setAddr(addr);
        c.setName(name);
        c.setTel_m(tel_m);
        c.setTel_s(tel_s);

        return c;
    }

}
