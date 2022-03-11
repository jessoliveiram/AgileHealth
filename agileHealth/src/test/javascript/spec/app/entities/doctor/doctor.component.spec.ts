/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import DoctorComponent from '@/entities/doctor/doctor.vue';
import DoctorClass from '@/entities/doctor/doctor.component';
import DoctorService from '@/entities/doctor/doctor.service';

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
  describe('Doctor Management Component', () => {
    let wrapper: Wrapper<DoctorClass>;
    let comp: DoctorClass;
    let doctorServiceStub: SinonStubbedInstance<DoctorService>;

    beforeEach(() => {
      doctorServiceStub = sinon.createStubInstance<DoctorService>(DoctorService);
      doctorServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<DoctorClass>(DoctorComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          doctorService: () => doctorServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      doctorServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllDoctors();
      await comp.$nextTick();

      // THEN
      expect(doctorServiceStub.retrieve.called).toBeTruthy();
      expect(comp.doctors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      doctorServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeDoctor();
      await comp.$nextTick();

      // THEN
      expect(doctorServiceStub.delete.called).toBeTruthy();
      expect(doctorServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
