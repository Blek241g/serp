interface Subitem{
  path:string;
  title:string;
}

export interface Item{
  path:string;
  icon:string;
  title:string;
}

export const NAV_ITEMS: Item[] = [
  {
    path: "dashboard",
    icon: "dashboard",
    title: "Dashboard",
  },
  {
    path: "users",
    icon: "groups",
    title: "Users",
  },
  {
    path: "plans",
    icon: "bookmark",
    title: "Plans",
  },
]
