import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/pages/Login'
import MailBox from '@/components/pages/mail/MailBox'
import NewMail from '@/components/pages/mail/NewMail'
import UserRegistrationDataEntry from '@/components/pages/user-registration/UserRegistrationDataEntry'
import UserRegistrationSuccess from '@/components/pages/user-registration/UserRegistrationSuccess'
import UserList from '@/components/pages/user-management/UserList'
import NearExpireUsers from '@/components/pages/user-management/NearExpireUsers'
import CorporateCustomerDataEntry from '@/components/pages/corporate-customer/CorporateCustomerDataEntry'
import CorporateCustomersList from '@/components/pages/corporate-customer/CorporateCustomersList'
import CorporateCustomerDetails from '@/components/pages/corporate-customer/CorporateCustomerDetails'
import CorporateCustomerNewUserRegistration from '@/components/pages/corporate-customer/CorporateCustomerNewUserRegistration'
import Home from '@/components/pages/Home'
import SignUpRequests from '@/components/pages/signup-requests/SignUpRequests'
import AdminRegistration from '@/components/pages/admin-registration/AdminRegistration'
import AdminRegistrationSuccess from '@/components/pages/admin-registration/AdminRegistrationSuccess'
import AdminList from '@/components/pages/admin-management/AdminList'
import OrderManagement from '@/components/pages/orders/OrderManagement'
import NewOrder from '@/components/pages/orders/NewOrder'
import NewPromotion from '@/components/pages/promotions/NewPromotion'
import PromotionsList from '@/components/pages/promotions/PromotionsList'
import NewTransaction from '@/components/pages/transactions/NewTransaction'
import TransactionList from '@/components/pages/transactions/TransactionList'
import NewEmail from '@/components/pages/email/NewEmail'

Vue.use(Router)


var router = new Router({
  mode: 'history',
  linkExactActiveClass: 'active',
  routes: [
    {
      path: '/',
      redirect: '/login',
      beforeRouteEnter (to, from, next) {
        next('/login')
      }
    },{
      path: '/home',
      name: 'Home',
      component: Home,
      meta: {authenticated: true}
    },{
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {authenticated: false}
    },{
      path: '/mailbox',
      name: 'MailBox',
      component: MailBox,
      meta: {authenticated: true}
    },{
      path: '/newmail',
      name: 'NewMail',
      component: NewMail,
      meta: {authenticated: true}
    },{
      path: '/userregistration/dataentry',
      name: 'UserRegistrationDataEntry',
      component: UserRegistrationDataEntry,
      meta: {authenticated: true}
    },{
      path: '/userregistration/success/:nationalCode',
      name: 'UserRegistrationSuccess',
      component: UserRegistrationSuccess,
      meta: {authenticated: true}
    },{
      path: '/usermanagement/list',
      name: 'UserList',
      component: UserList,
      meta: {authenticated: true}
    },{
      path: '/usermanagement/nearexpire',
      name: 'NearExpireUsers',
      component: NearExpireUsers,
      meta: {authenticated: true}
    },{
      path: '/corporatecustomer/registration',
      name: 'CorporateCustomerDataEntry',
      component: CorporateCustomerDataEntry,
      meta: {authenticated: true}
    },{
      path: '/corporatecustomer/list',
      name: 'CorporateCustomersList',
      component: CorporateCustomersList,
      meta: {authenticated: true}
    },{
      path: '/corporatecustomer/details/:corporateCustomerId',
      name: 'CorporateCustomerDetails',
      component: CorporateCustomerDetails,
      meta: {authenticated: true}
    },{
      path: '/corporatecustomer/newuser/:corporateCustomerId',
      name: 'CorporateCustomerNewUserRegistration',
      component: CorporateCustomerNewUserRegistration,
      meta: {authenticated: true}
    },{
      path: '/signuprequests',
      name: 'SignUpRequests',
      component: SignUpRequests,
      meta: {authenticated: true}
    },{
      path: '/adminregistration/dataentry',
      name: 'AdminRegistration',
      component: AdminRegistration,
      meta: {authenticated: true}
    },{
      path: '/adminregistration/success/:nationalCode',
      name: 'AdminRegistrationSuccess',
      component: AdminRegistrationSuccess,
      meta: {authenticated: true}
    },{
      path: '/adminmanagement/list',
      name: 'AdminList',
      component: AdminList,
      meta: {authenticated: true}
    },{
      path: '/orders',
      name: 'OrderManagement',
      component: OrderManagement,
      meta: {authenticated: true}
    },{
      path: '/neworder',
      name: 'NewOrder',
      component: NewOrder,
      meta: {authenticated: true}
    },{
      path: '/promotions/newpromotion',
      name: 'NewPromotion',
      component: NewPromotion,
      meta: {authenticated: true}
    },{
      path: '/promotions/list',
      name: 'PromotionsList',
      component: PromotionsList,
      meta: {authenticated: true}
    },{
      path: '/transactions/new',
      name: 'NewTransaction',
      component: NewTransaction,
      meta: {authenticated: true}
    },{
      path: '/transactions/list',
      name: 'TransactionList',
      component: TransactionList,
      meta: {authenticated: true}
    },{
      path: '/newemail',
      name: 'NewEmail',
      component: NewEmail,
      meta: {authenticated: true}
    }
  ]
})

export default router;
