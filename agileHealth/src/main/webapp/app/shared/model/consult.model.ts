export interface IConsult {
  id?: number;
  mode?: string | null;
  medicalSpecialty?: string | null;
  local?: string | null;
  doctorName?: string | null;
  date?: Date | null;
}

export class Consult implements IConsult {
  constructor(
    public id?: number,
    public mode?: string | null,
    public medicalSpecialty?: string | null,
    public local?: string | null,
    public doctorName?: string | null,
    public date?: Date | null
  ) {}
}
