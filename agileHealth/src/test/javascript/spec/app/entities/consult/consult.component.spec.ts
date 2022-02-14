/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ConsultComponent from '@/entities/consult/consult.vue';
import ConsultClass from '@/entities/consult/consult.component';
import ConsultService from '@/entities/consult/consult.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Consult Management Component', () => {
    let wrapper: Wrapper<ConsultClass>;
    let comp: ConsultClass;
    let consultServiceStub: SinonStubbedInstance<ConsultService>;

    beforeEach(() => {
      consultServiceStub = sinon.createStubInstance<ConsultService>(ConsultService);
      consultServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ConsultClass>(ConsultComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          consultService: () => consultServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      consultServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllConsults();
      await comp.$nextTick();

      // THEN
      expect(consultServiceStub.retrieve.called).toBeTruthy();
      expect(comp.consults[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
