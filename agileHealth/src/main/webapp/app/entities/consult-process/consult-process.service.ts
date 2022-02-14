import axios from 'axios';

import { IConsultProcess } from '@/shared/model/consult-process.model';

const baseApiUrl = 'api/consult-processes';

export default class ConsultProcessService {
  public find(id: number): Promise<IConsultProcess> {
    return new Promise<IConsultProcess>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IConsultProcess): Promise<IConsultProcess> {
    return new Promise<IConsultProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
