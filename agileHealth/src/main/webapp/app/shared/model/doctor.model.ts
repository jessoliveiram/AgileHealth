export interface IDoctor {
  id?: number;
  name?: string | null;
}

export class Doctor implements IDoctor {
  constructor(public id?: number, public name?: string | null) {}
}
