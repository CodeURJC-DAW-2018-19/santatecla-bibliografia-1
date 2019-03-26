export class Tab{
    constructor(private name: string, private url: string){}
    get Name():string{
        return this.name;
    }
}