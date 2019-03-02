package com.example.administrator.demo_shoppingcar.fragment.bean;

import java.util.List;

/**
 * Author : 张自力
 * Created on time.
 */

public class ByCarBean {


    /**
     * result : [{"commodityId":11,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵","count":2,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/1/1.jpg","price":9},{"commodityId":21,"commodityName":"【加绒休闲 舒适轻便】秋冬增高休闲鞋厚底棉鞋运动户外通勤简约韩版女鞋","count":2,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/4/1.jpg","price":189},{"commodityId":7,"commodityName":"蓝色之恋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/5/1.jpg","price":29},{"commodityId":12,"commodityName":"Lara style美妆BB蛋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/2/1.jpg","price":22},{"commodityId":25,"commodityName":"秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/1/1.jpg","price":158}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 11
         * commodityName : 盒装葫芦粉扑美妆蛋化妆海绵
         * count : 2
         * pic : http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/1/1.jpg
         * price : 9
         */

        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
