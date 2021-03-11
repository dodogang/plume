# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Unreleased

## v0.2.0 - 2021-03-11
### Added
- Add `BlockBatchedRegistry` that wraps around `BatchedRegistry` to make it 
  easier to register blocks and their block items.
- Rename `BatchedRegister` to `BatchedRegistry`.
- Add a changelog to keep track of changes between versions. This should make
  it easier to keep track of what needs to be changed when a dependent changes
  its plume version.

### Removed
- Remove `BatchedRegister`. Instead, use `BatchedRegistry` because that name
  works better grammatically.

## v0.1.0 - 2021-03-10
### Added
- Add `ModEventBus` that lets dependent mods register their forge mod event bus
  to Plume. Plume then can easily register events for the mod under that bus.
- Add `ItemGroupBuilder` that lets dependent mods create ItemGroups without a
  specific platform.
- Add `RegistrySupplier` that holds a delayed reference to a registry object.
  This allows for forge registry replacement to still work.
- Add `BatchedRegister` that serves as a wrapper around registering any object
  to vanilla or forge registries.