import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/pages/Login'
import Profile from '@/components/pages/Profile'
import SignUpRequest from '@/components/pages/SignUpRequest'
import ResetPassword from '@/components/pages/ResetPassword'
import Orders from '@/components/pages/orders/Orders'
import NewOrder from '@/components/pages/orders/NewOrder'
import Messages from '@/components/pages/messages/Messages'
import Transactions from '@/components/pages/transactions/Transactions'
import UsernameVerification from '@/components/pages/login/UsernameVerification'
import AuthorizationCode from '@/components/pages/login/AuthorizationCode'


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
      path: '/profile',
      name: 'Profile',
      component: Profile
    },{
      path: '/orders',
      name: 'Orders',
      component: Orders
    },{
      path: '/neworder',
      name: 'NewOrder',
      component: NewOrder
    },{
      path: '/messages',
      name: 'Messages',
      component: Messages
    },{
      path: '/transactions',
      name: 'Transactions',
      component: Transactions
    },{
    //   path: '/userpass',
    //   name: 'Login',
    //   component: Login,
    //   meta: {authenticated: false}
    // },{
    //   path: '/authorizationcode/:username/:phonenumber',
    //   name: 'AuthorizationCode',
    //   component: AuthorizationCode,
    //   meta: {authenticated: false}
    // },{
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {authenticated: false},
      beforeRouteEnter (to, from, next) {
        next('/login')
      }
    },{
    //   path: '/signup',
    //   name: 'SignUpRequest',
    //   component: SignUpRequest,
    //   meta: {authenticated: false}
    // },{
      path: '/resetpassword',
      name: 'ResetPassword',
      component: ResetPassword,
      meta: {authenticated: true}
    },{
      path: '*',
      redirect: '/login'
    }
  ]
})


export default router;
