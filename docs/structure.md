Each Provider (Bank) will have it's own class.

That class will implement an interface covering the individual endpoints for that type of API

Each API endpint method will return a *Request object

That request object has sync/async methods which then execute the request