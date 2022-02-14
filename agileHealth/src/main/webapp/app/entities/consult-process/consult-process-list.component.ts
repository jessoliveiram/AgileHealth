import { Component, Vue, Inject } from 'vue-property-decorator';
import { IConsultProcess } from '@/shared/model/consult-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import ConsultProcessService from './consult-process.service';

@Component
export default class ConsultProcessListComponent extends Vue {
  @Inject('consultProcessService') private consultProcessService: () => ConsultProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ConsultProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public consultProcessList: IConsultProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.consultProcessService()
      .retrieve()
      .then(
        res => {
          this.consultProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
