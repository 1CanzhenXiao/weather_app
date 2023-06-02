package com.example.weather_app.juhe;
/*
* 相关指数信息
* */
public class JHIndexBean {

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {

        private String city;
        private LifeBean life;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public LifeBean getLife() {
            return life;
        }

        public void setLife(LifeBean life) {
            this.life = life;
        }

        public static class LifeBean {

            private KongtiaoBean kongtiao;
            private GuominBean guomin;
            private ShushiduBean shushidu;
            private ChuanyiBean chuanyi;
            private DiaoyuBean diaoyu;
            private GanmaoBean ganmao;
            private ZiwaixianBean ziwaixian;
            private XicheBean xiche;
            private YundongBean yundong;
            private DaisanBean daisan;

            public KongtiaoBean getKongtiao() {
                return kongtiao;
            }

            public void setKongtiao(KongtiaoBean kongtiao) {
                this.kongtiao = kongtiao;
            }

            public GuominBean getGuomin() {
                return guomin;
            }

            public void setGuomin(GuominBean guomin) {
                this.guomin = guomin;
            }

            public ShushiduBean getShushidu() {
                return shushidu;
            }

            public void setShushidu(ShushiduBean shushidu) {
                this.shushidu = shushidu;
            }

            public ChuanyiBean getChuanyi() {
                return chuanyi;
            }

            public void setChuanyi(ChuanyiBean chuanyi) {
                this.chuanyi = chuanyi;
            }

            public DiaoyuBean getDiaoyu() {
                return diaoyu;
            }

            public void setDiaoyu(DiaoyuBean diaoyu) {
                this.diaoyu = diaoyu;
            }

            public GanmaoBean getGanmao() {
                return ganmao;
            }

            public void setGanmao(GanmaoBean ganmao) {
                this.ganmao = ganmao;
            }

            public ZiwaixianBean getZiwaixian() {
                return ziwaixian;
            }

            public void setZiwaixian(ZiwaixianBean ziwaixian) {
                this.ziwaixian = ziwaixian;
            }

            public XicheBean getXiche() {
                return xiche;
            }

            public void setXiche(XicheBean xiche) {
                this.xiche = xiche;
            }

            public YundongBean getYundong() {
                return yundong;
            }

            public void setYundong(YundongBean yundong) {
                this.yundong = yundong;
            }

            public DaisanBean getDaisan() {
                return daisan;
            }

            public void setDaisan(DaisanBean daisan) {
                this.daisan = daisan;
            }

            public static class KongtiaoBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class GuominBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class ShushiduBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class ChuanyiBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class DiaoyuBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class GanmaoBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class ZiwaixianBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class XicheBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class YundongBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }

            public static class DaisanBean {

                private String v;
                private String des;

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }
            }
        }
    }
}