# Hexagonal Architecture

There is 3 layers in the hexagonal architecture:

- The domain layer
- The application layer
- The infrastructure layer

## Gateways

Gateways are interfaces implemented by secondary adapters from the infrastructure layer.

They are used by the use cases to access the secondary adapters.

They belong to the domain layer.

## Ports

### Primary Ports

Primary ports are interfaces implemented by usecases.

They are used by the primary adapters to access the usecases from the domain layer.

They belong to the domain layer.

### Secondary Ports

Secondary ports are interfaces implemented by secondary adapters from the infrastructure layer.

They are used by the usecases to access the secondary adapters.

They belong to the domain layer.

## Adapters

### Primary Adapters

Primary adapters expose the domain API to the user.

They implement ports from the domain layer.

They belong to the application layer.

### Secondary Adapters

Secondary adapters implement secondary ports from the domain layer.

They belong to the infrastructure layer.

They are used by the application layer to inject infrastructure dependencies into the domain layer.

In the infrastructure layer, secondary adapters are the only classes visible from the outside.

Other classes like persistence entities should therefore be hidden with the keyword `internal`.

## Usecases

Usecases implement ports from the domain layer.

They belong to the domain layer.

They contain the business logic.

They use secondary ports to access backing services like databases or external APIs.

They are exposed to the outside world through primary adapters.

## Model

The model is made of business entities and value objects. It belongs to the domain layer.

It is used by the usecases by its aggregate-roots objects.

It is a representation of the business problems. It is not an accurate representation, but a useful one. The only accurate model of a thing is the thing itself.

It contains the Data Transfer Objects (DTO) used by the primary Adapters to communicate with the outside world.
 DTOs are representations of something that transited through a boundary, so they have no reason to change (immutable) and have no behavior.

The model should bot be corrupted by other business models. Use an anti-corruption layer to translate data from other models to your model.
