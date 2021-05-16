# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Unreleased

## v0.5.4 - 2021-5-16
### Added
- Add `RenderLayerRegistry` for setting a block or fluid's render layer.

## v0.5.3 - 2021-5-16
### Added
- Add `AbstractBlockStateMixin` which allows for blocks with the "bookshelves"
  tag to affect enchanting tables.
- Add `PlumeTags` which makes it simple to access platform specific tags.

## v0.5.2 - 2021-05-15
### Added
- Add `PlumeLadderBlock` which makes vanilla `LadderBlock` public.

## v0.5.1 - 2021-05-09
### Added
- Add `Environment` for getting the platform and whether the game is in a
  development environment or not.
  
### Changed
- Remove check in `FuelRegistry` so it can be used before tags have been
  initialized.

## v0.5.0 - 2021-04-01
### Removed
- Remove `registerTag` from `FuelRegistry` because it won't be used.

### Fixed
- Fix `register` method in `FuelRegistry` to actually work.

## v0.4.0 - 2021-03-28
### Added
- Add `TagRegistry` for registering block, entity type, fluid, and item tags.
- Add `registerTag` in `FuelRegistry` for registering whole tags as a fuel.

### Changed
- Change parameters on `register` in `FuelRegistry` to take multiple items in
  one method call.
  
### Removed
- Remove `unregister` in `FuelRegistry` as it was unnecessary and not
  implemented well.

## v0.3.3 - 2021-03-27
- Add `FuelRegistry` for registering items as furnace fuels and for getting
  the fuel time of an item.

## v0.3.2 - 2021-03-27
- Add `ToolTags` which allows mods to find if an ItemStack contains a tool.
- Add `BlockEntityRendererRegistry` to register block entity renderers to block
  entity types.
- Add `BuiltinItemRendererRegistry` to register dynamic item renderers to items.
- Add `SpriteRegistry`
- Add `InteractionCallback.RightClickBlock` event abstraction.

## v0.3.1 - 2021-03-24
### Added
- Add `PointOfInterestTypeAppender` which allows for mods to add blocks to
  vanilla POI types' block state list.

## v0.3.0 - 2021-03-11
### Added
- Add `PlumeCraftingTableBlock` which allows for non-vanilla crafting table
  blocks to open the crafting screen.
- Add `PlumeStairsBlock` which makes vanilla `StairsBlock` public.
- Add a helper function to add blocks with copied block settings to
  `BlockBatchedRegistry`.
- Add a helper function to add stairs to `BlockBatchedRegistry`.

### Changed
- Rename `BatchedRegistry` to `RegistryBatch`.
- Rename `BlockBatchedRegistry` to `BlockRegistryBatch`.

## v0.2.0 - 2021-03-11
### Added
- Add `BlockBatchedRegistry` that wraps around `BatchedRegistry` to make it 
  easier to register blocks and their block items.
- Add a changelog to keep track of changes between versions. This should make
  it easier to keep track of what needs to be changed when a dependent changes
  its plume version.

### Changed
- Rename `BatchedRegister` to `BatchedRegistry`.

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