import {RouteRecordRaw} from 'vue-router';

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'home',
        redirect: '/login',
        meta: {
            title: '首页',
            renderMenu: false,
            icon: 'CreditCardOutlined',
        },
    },
    {
        path: '/login',
        name: '登录',
        meta: {
            icon: 'LoginOutlined',
            view: 'blank',
            target: '_blank',
            cacheable: false,
        },
        component: () => import('@/pages/login'),
    },
    /**
     * target：'_self'在页面的元数据中打开，表示在当前页面打开而不是新页面中打开。
     * target: '_blank'表示在新页面中打开
     */
    {
        path: '/temp-system',
        name: '系统配置',
        meta: {
            icon: 'SettingOutlined',
            renderMenu: true,
        },
        component: () => import('@/components/layout/BlankView.vue'),
        children: [
            {
                path: '/role',
                name: '角色管理',
                meta: {
                    icon: 'LoginOutlined',
                    target: '_self',
                    cacheable: true,
                    renderMenu: true
                },
                component: () => import('@/pages/system/role/index'),
            },
        ]
    },
    /* {
       path: '/front',
       name: '前端',
       meta: {
         renderMenu: false,
       },
       component: () => import('@/components/layout/FrontView.vue'),
       children: [

         {
           path: '/home',
           name: '首页',
           meta: {
             view: 'blank',
           },
           component: () => import('@/pages/home'),
         },
       ],
     },*/
    {
        path: '/403',
        name: '403',
        props: true,
        meta: {
            renderMenu: false,
        },
        component: () => import('@/pages/Exp403.vue'),
    },
    {
        path: '/:pathMatch(.*)*',
        name: '404',
        props: true,
        meta: {
            icon: 'CreditCardOutlined',
            renderMenu: false,
            cacheable: false,
            _is404Page: true,
        },
        component: () => import('@/pages/Exp404.vue'),
    },
];

export default routes;
