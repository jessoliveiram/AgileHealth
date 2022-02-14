/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ConsultDetailComponent from '@/entities/consult/consult-details.vue';
import ConsultClass from '@/entities/consult/consult-details.component';
import ConsultService from '@/entities/consult/consult.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Consult Management Detail Component', () => {
    let wrapper: Wrapper<ConsultClass>;
    let comp: ConsultClass;
    let consultServiceStub: SinonStubbedInstance<ConsultService>;

    beforeEach(() => {
      consultServiceStub = sinon.createStubInstance<ConsultService>(ConsultService);

      wrapper = shallowMount<ConsultClass>(ConsultDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { consultService: () => consultServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundConsult = { id: 123 };
        consultServiceStub.find.resolves(foundConsult);

        // WHEN
        comp.retrieveConsult(123);
        await comp.$nextTick();

        // THEN
        expect(comp.consult).toBe(foundConsult);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundConsult = { id: 123 };
        consultServiceStub.find.resolves(foundConsult);

        // WHEN
        comp.beforeRouteEnter({ params: { consultId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.consult).toBe(foundConsult);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
