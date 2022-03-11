import { IDoctor } from '@/shared/model/doctor.model';

export interface IConsult {
  id?: number;
  mode?: string | null;
  medicalSpecialty?: string | null;
  local?: string | null;
  date?: Date | null;
  doctor?: IDoctor | null;
}

export class Consult implements IConsult {
  constructor(
    public id?: number,
    public mode?: string | null,
    public medicalSpecialty?: string | null,
    public local?: string | null,
    public date?: Date | null,
    public doctor?: IDoctor | null
  ) {}
}
