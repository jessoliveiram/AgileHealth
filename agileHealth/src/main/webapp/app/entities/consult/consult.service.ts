import axios from 'axios';

import { IConsult } from '@/shared/model/consult.model';

const baseApiUrl = 'api/consults';

export default class ConsultService {
  public find(id: number): Promise<IConsult> {
    return new Promise<IConsult>((resolve, reject) => {
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
}
