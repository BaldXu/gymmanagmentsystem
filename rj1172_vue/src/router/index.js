import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login";
import Index from "../components/Index";
import EquipmentRent from "../components/EquipmentRent";
import EquipmentQuery from "../components/EquipmentQuery";
import EquipmentReturn from "../components/EquipmentReturn";
import EquipmentStandard from "../components/EquipmentStandard";
import EquipmentStandardEdit from "../components/EquipmentStandardEdit";
import EquipmentOrderRecoverConfirm from "../components/EquipmentOrderRecoverConfirm";
import EquipmentReturnCheck from "../components/EquipmentReturnCheck";
import EquipmentReturnOrderCheck from "../components/EquipmentReturnOrderCheck";
import EquipmentOrder from "../components/EquipmentOrder";
import Useradd from "../components/Useradd";
import Userinfo from "../components/Userinfo";
import Usersecurity from "../components/Usersecurity";
import Userquery from "../components/Userquery";

//场地管理
import PlaceNews from "../components/PlaceNews";
import PlaceMsg from "../components/PlaceMsg";
import PlaceOrder from "../components/PlaceOrder";
import PlaceOrderMsg from "../components/PlaceOrderMsg";
import PlaceAnnouncement from "../components/PlaceAnnouncement";
import QueryWeekMsg from "../components/QueryWeekMsg";
import OrderOfTeacher from "../components/OrderOfTeacher";
import OrderOfAssociation from "../components/OrderOfAssociation";
import OrderBreach from "../components/OrderBreach";

//赛事创建
import MatchCreate from "../components/MatchCreate";
import MatchCancel from "../components/MatchCancel";
import MatchQuery from "../components/MatchQuery";
import MatchPlaceApply from "../components/MatchPlaceApply";
import MatchEquipmentApply from "../components/MatchEquipmentApply";
import MatchIntro from "../components/MatchIntro";
//赛事创建

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
};

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    redirect:'/login',
    show:false
  },
  {
    path:'/login',
    name:'login',
    component: Login,
    show:false
  },
  {
    path:'/user',
    name:'用户管理',
    component: Index,
    src:"el-icon-location",
    show:true,
    children:[{
      path:'/Useradd',
      name:'添加用户',
      component: Useradd,
      show:true
    },
      {
        path:'/Userinfo',
        name:'修改信息',
        component: Userinfo,
        show:true
      },
      {
        path:'/Usersecurity',
        name:'修改密码',
        component: Usersecurity,
        show:true
      },
      {
        path:'/Userquery',
        name:'信息查询',
        component: Userquery,
        show:true
      },
    ]
  },
  {
    path:'/place',
    name:'场地管理',
    component: Index,
    src:"el-icon-menu",
    show:true,
    children:[
      {
        path:'/placenews',
        name:'收费信息',
        component: PlaceNews,
        show:true
      },
      {
        path:'/placeannouncement',
        name:'场馆公告',
        component: PlaceAnnouncement,
        show:true
      },
      {
        path:'/placemsg',
        name:'场地信息',
        component: PlaceMsg,
        show:true
      },
      {
        path:'/placeorder',
        name:'场地预约',
        component: PlaceOrder,
        show:true
      },
      {
        path:'/placeordermsg',
        name:'预约信息',
        component: PlaceOrderMsg,
        show:true
      },
      {
        path:'/queryweekmsg',
        name:'一周信息',
        component: QueryWeekMsg,
        show:true
      },
      {
        path:'/teacherorder',
        name:'老师用地',
        component: OrderOfTeacher,
        show:true
      },
      {
        path:'/associationorder',
        name:'社团用地',
        component: OrderOfAssociation,
        show:true
      },
      {
        path:'/orderbreach',
        name:'失约处理',
        component: OrderBreach,
        show:true
      },
    ]
  },
  {
    path:'/game',
    name:'赛事管理',
    component: Index,
    src:"el-icon-document",
    show:true,
    children:[
      {
        path: '/mathchcreate',
        name:'赛事创建',
        component: MatchCreate,
        show: true
      },
      {
        path: '/mathccancel',
        name:'赛事取消',
        component: MatchCancel,
        show: true
      },
      {
        path: '/mathcquery',
        name:'赛事查询',
        component: MatchQuery,
        show: true
      },
      {
        path: '/matchplaceapply',
        name:'赛事场地申请',
        component: MatchPlaceApply,
        show: true
      },
      {
        path: '/matchequipmentapply',
        name:'赛事器材申请',
        component: MatchEquipmentApply,
        show: true
      },
      {
        path: '/matchintro',
        name:'裁判简介公告',
        component: MatchIntro,
        show: true
      },
    ]
  },
  {
    path:'/equipment',
    name:'器材管理',
    component: Index,
    src:"el-icon-setting",
    show:true,
    children: [
      {
        path:'/equipmentquery',
        name:'器材查询',
        component: EquipmentQuery,
        show:true
      },
      {
        path:'/equipmentrent',
        name:'器材租用',
        component: EquipmentRent,
        show:false
      },
      {
        path:'/equipmentreturn',
        name:'器材归还',
        component: EquipmentReturn,
        show:true
      },
      {
        path:'/equipmentorder',
        name:'器材订单',
        component: EquipmentOrder,
        show:true
      },
      {
        path:'/equipmentstandard',
        name:'租用消费标准',
        component: EquipmentStandard,
        show:true
      },
      {
        path:'/equipmentstandardedit',
        name:'租用消费标准修改',
        component: EquipmentStandardEdit,
        show:false
      },
      {
        path:'/equipmentorderrecoverconfirm',
        name:'器材回收确认',
        component: EquipmentOrderRecoverConfirm,
        show:false
      },
      {
        path:'/equipmentreturncheck',
        name:'器材归还确认',
        component: EquipmentReturnCheck,
        show:false
      },
      {
        path:'/equipmentreturnordercheck',
        name:'器材归还订单查看',
        component: EquipmentReturnOrderCheck,
        show:false
      }
    ]
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
