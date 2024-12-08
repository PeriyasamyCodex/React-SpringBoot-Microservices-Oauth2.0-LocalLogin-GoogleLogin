export default interface Attraction {
    _id: string,
    title: string,
    description: string,
    distanceFromCenter: string,
    location: string,
    images: string[],
    openTime: string,
    closeTime: string,
    spendDuration: number,
    RecommendedPersonType: string,
    PhysicalFitnessLevel: string,
    WheelchairAccessability: boolean,
    TerrainType: string,
    name: string,
}