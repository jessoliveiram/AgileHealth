import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Consult = () => import('@/entities/consult/consult.vue');
// prettier-ignore
const ConsultDetails = () => import('@/entities/consult/consult-details.vue');
// prettier-ignore
const ConsultProcessDetails = () => import('@/entities/consult-process/consult-process-details.vue');
// prettier-ignore
const ConsultProcessList = () => import('@/entities/consult-process/consult-process-list.vue');
// prettier-ignore
const ConsultStartFormInit = () => import('@/entities/consult-process/consult-start-form-init.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/consult',
    name: 'Consult',
    component: Consult,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/consult/:consultId/view',
    name: 'ConsultView',
    component: ConsultDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ConsultProcess/instance/:processInstanceId/view',
    name: 'ConsultProcessView',
    component: ConsultProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ConsultProcess/instances',
    name: 'ConsultProcessList',
    component: ConsultProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ConsultProcess/init',
    name: 'ConsultStartFormInit',
    component: ConsultStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
