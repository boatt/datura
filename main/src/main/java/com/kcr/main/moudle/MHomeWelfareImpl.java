package com.kcr.main.moudle;

import com.dtr.network.bean.BannerBean;
import com.kcr.common.base.BaseModel;
import com.kcr.common.util.RxObservable;
import com.kcr.main.R;
import com.kcr.main.bean.HomeWelfareBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MHomeWelfareImpl extends BaseModel {

    public void mHomeWelfare(final RxObservable rxObservable) {
        ArrayList<HomeWelfareBean> data = new ArrayList<>();
        data.add(new HomeWelfareBean(R.mipmap.img_main_orphan, "贫困孤儿助养", "旨在帮助中国贫困地区6周岁以上的孤儿，为他们提供基本生活和学习补助，使他们能和其他孩子一样享有受教育的权利，得到关怀和照顾，快乐健康成长。"));
        data.add(new HomeWelfareBean(R.mipmap.img_main_book, "益童书屋", "项目旨在为贫困山区、留守儿童等一些渴望读书的孩子筹集课外读物，协助孩子所在的学校筹建起自己的阅览室，并且协助学校的老师进行阅读推广。"));
        data.add(new HomeWelfareBean(R.mipmap.skid_right_5, "贫困白内障的光明", "您的善款将用于为贫困地区的白内障患者提供治疗的手术费，通过手术可以帮助他们重见光明。"));
        data.add(new HomeWelfareBean(R.mipmap.icon_main_emptynest, "空巢不空银天使计划", "空巢老人，一个不起眼的群体，但或许其中就有你我最亲近的人。病痛的折磨只是他们生活里的插曲，无人问津的寂寞和“被遗弃”的孤独感才是最大的伤害。"));
//        data.add(new HomeWelfareBean(R.mipmap.img_main_orphan, "贫困孤儿助养", "旨在帮助中国贫困地区6周岁以上的孤儿，为他们提供基本生活和学习补助，使他们能和其他孩子一样享有受教育的权利，得到关怀和照顾，快乐健康成长。"));
//        data.add(new HomeWelfareBean(R.mipmap.img_main_book, "益童书屋", "项目旨在为贫困山区、留守儿童等一些渴望读书的孩子筹集课外读物，协助孩子所在的学校筹建起自己的阅览室，并且协助学校的老师进行阅读推广。"));
//        data.add(new HomeWelfareBean(R.mipmap.skid_right_5, "贫困白内障的光明", "您的善款将用于为贫困地区的白内障患者提供治疗的手术费，通过手术可以帮助他们重见光明。"));
//        data.add(new HomeWelfareBean(R.mipmap.icon_main_emptynest, "空巢不空银天使计划", "空巢老人，一个不起眼的群体，但或许其中就有你我最亲近的人。病痛的折磨只是他们生活里的插曲，无人问津的寂寞和“被遗弃”的孤独感才是最大的伤害。"));
        rxObservable.onNext(data);
//        Observable.timer(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                ArrayList<HomeWelfareBean> data = new ArrayList<>();
//                data.add(new HomeWelfareBean(R.mipmap.img_main_orphan, "贫困孤儿助养", "旨在帮助中国贫困地区6周岁以上的孤儿，为他们提供基本生活和学习补助，使他们能和其他孩子一样享有受教育的权利，得到关怀和照顾，快乐健康成长。"));
//                data.add(new HomeWelfareBean(R.mipmap.img_main_book, "益童书屋", "项目旨在为贫困山区、留守儿童等一些渴望读书的孩子筹集课外读物，协助孩子所在的学校筹建起自己的阅览室，并且协助学校的老师进行阅读推广。"));
//                data.add(new HomeWelfareBean(R.mipmap.skid_right_5, "贫困白内障的光明", "您的善款将用于为贫困地区的白内障患者提供治疗的手术费，通过手术可以帮助他们重见光明。"));
//                data.add(new HomeWelfareBean(R.mipmap.icon_main_emptynest, "空巢不空银天使计划", "空巢老人，一个不起眼的群体，但或许其中就有你我最亲近的人。病痛的折磨只是他们生活里的插曲，无人问津的寂寞和“被遗弃”的孤独感才是最大的伤害。"));
//                data.add(new HomeWelfareBean(R.mipmap.img_main_orphan, "贫困孤儿助养", "旨在帮助中国贫困地区6周岁以上的孤儿，为他们提供基本生活和学习补助，使他们能和其他孩子一样享有受教育的权利，得到关怀和照顾，快乐健康成长。"));
//                data.add(new HomeWelfareBean(R.mipmap.img_main_book, "益童书屋", "项目旨在为贫困山区、留守儿童等一些渴望读书的孩子筹集课外读物，协助孩子所在的学校筹建起自己的阅览室，并且协助学校的老师进行阅读推广。"));
//                data.add(new HomeWelfareBean(R.mipmap.skid_right_5, "贫困白内障的光明", "您的善款将用于为贫困地区的白内障患者提供治疗的手术费，通过手术可以帮助他们重见光明。"));
//                data.add(new HomeWelfareBean(R.mipmap.icon_main_emptynest, "空巢不空银天使计划", "空巢老人，一个不起眼的群体，但或许其中就有你我最亲近的人。病痛的折磨只是他们生活里的插曲，无人问津的寂寞和“被遗弃”的孤独感才是最大的伤害。"));
//
//                rxObservable.onNext(data);
//            }
//        });
    }

    public void mBanner(final RxObservable rxObservable) {
        Observable.timer(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                List<BannerBean> list = new ArrayList<>();
                list.add(new BannerBean("愿你在被打击时，记起你的珍贵，抵抗恶意。"));
                list.add(new BannerBean("这个世界缺的不是完美的人,而是从心底给出的真心,正义,无畏与同情。"));
                list.add(new BannerBean("[广告] 我们不生产水,我们只是大自然的搬运工!农夫山泉"));
                list.add(new BannerBean("你看到什么,听到什么,做什么和谁在一起,有一种从心灵深处漫溢出的不懊悔也不羞耻的平和与喜悦。"));
                list.add(new BannerBean("这一次让我们一起用真心去温暖世界"));
                list.add(new BannerBean("[广告] 你周围的人都在玩抖音APP快去了解一下吧!"));
                rxObservable.onNext(list);
            }
        });

    }

}
