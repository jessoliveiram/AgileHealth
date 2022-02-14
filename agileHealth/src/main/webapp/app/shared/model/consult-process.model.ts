import { IConsult } from '@/shared/model/consult.model';

export interface IConsultProcess {
  id?: number;
  processInstance?: any | null;
  consult?: IConsult | null;
}

export class ConsultProcess implements IConsultProcess {
  constructor(public id?: number, public processInstance?: any | null, public consult?: IConsult | null) {}
}
