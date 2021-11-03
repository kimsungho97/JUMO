import { Route } from "react-router-dom";
import { Login } from "./pages";
import { useRecoilValue } from "recoil";
import { userAtom } from "./store/user";



export const RouteIf = ({component: Component, ...rest }) => {
    const user = useRecoilValue(userAtom);
    return (
        <Route
            {...rest}
            render={
                props => {
                    if (user.userId === undefined) {
                        return <Login/>
                    }

                    if (Component) {
                        return <Component {...props}/>
                    }

                    return null;
                }
            }
        />
    )
}