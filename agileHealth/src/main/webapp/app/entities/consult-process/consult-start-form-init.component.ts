import { Component, Vue, Inject } from 'vue-property-decorator';

import DoctorService from '@/entities/doctor/doctor.service'; //
import { IDoctor } from '@/shared/model/doctor.model';

import { IConsultProcess, ConsultProcess } from '@/shared/model/consult-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Consult } from '@/shared/model/consult.model';
import ConsultProcessService from './consult-process.service';

const validations: any = {
  consultProcess: {
    consult: {
      mode: {},
      medicalSpecialty: {},
      local: {},
      date: {},
    },
  },
};

@Component({
  validations,
})
export default class ConsultStartFormInitComponent extends Vue {
  @Inject('consultProcessService') private consultProcessService: () => ConsultProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ConsultProcess';
  public consultProcess: IConsultProcess = new ConsultProcess();

  @Inject('doctorService') private doctorService: () => DoctorService;

  public doctors: IDoctor[] = [];

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initConsultStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.consultProcessService()
      .create(this.consultProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('agileHealthApp.consultStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initConsultStartForm(): void {
    this.consultProcess.consult = new Consult();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.consultProcess.processInstance = new ProcessInstance();
      this.consultProcess.processInstance.processDefinition = res;
    });
    this.doctorService()
      .retrieve()
      .then(res => {
        this.doctors = res.data;
      });
  }
}
