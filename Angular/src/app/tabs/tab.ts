export class Tab{
    constructor(private name: string, private url: string){}
    get Name():string{
        return this.name;
    }
    get Url():string{
        return this.url;
    }
}