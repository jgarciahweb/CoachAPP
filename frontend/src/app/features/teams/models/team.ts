import { Category } from './category';

export interface Team {
  id: string;
  name: string;
  categories: Category[];
}
